//
//  CompanyVO.m
//  sourcing
//
//  Created by wuzhu_mac on 13-5-21.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

#import "CompanyVO.h"
#import "ProjectCell.h"
#import "StringUtil.h"
#import "HtmlUtil.h"
#import "MobileInfoService.h"
#import "ResponseVO.h"
#import "JsonParser.h"
#import "CompanyDetailVO.h"


@interface CompanyVO()
{
@private
    NSInteger companyid;
}
@end
@implementation CompanyVO
@synthesize companyname = companyname_;
@synthesize industry = industry_;
@synthesize publishtime = publishtime_;

-(void)setCompanyid:(NSInteger)kid
{
    self.Id = kid;
    companyid = kid;
}

-(NSInteger)Companyid
{
    return companyid;
}

#pragma mark - Superclass Overrides
-(NSString *)getHtmlTitle
{
    return [NSString stringWithFormat:@"<br/><div align=\"center\"><font color=\"#111111\" size=\"4pt\"><strong>%@</strong></font></div><br/>",self.companyname];
}
-(NSString *)getHtmlSubTitle
{
    return [NSString stringWithFormat:@"<div align=\"center\"><font color=\"#666666\" size=\"2pt\">%@&nbsp;&nbsp;%@</font></div><div style=\"height:0;border-bottom:1px solid #f00\"></div>",self.publishtime,self.industry];
}

-(void)setHtmlToShow:(DetailInfoCompleteBlock)kcomblock failure:(DetailInfoErrorBlock)kerrorblock
{
    [super setHtmlToShow:kcomblock failure:kerrorblock];
    MobileInfoService *service = [[MobileInfoService alloc] initWithDelegate:self tag:CINT_TAG_GETCOMPANYDETAIL];
    service.tag2 = self.Companyid;
    [service getCompanyDetail:[NSString stringWithFormat:@"%d",self.Companyid]];
}
-(NSString *)generateShareText
{
    return self.companyname;
}

-(CGFloat)heightForCell:(NSInteger)kindex
{
    return 60.0f;
}

-(UITableViewCell *)tableView:(UITableView *)tableView index:(NSInteger)kindex
{
    static NSString *newsProjectCellIdentifier = @"ProjectCell";
    ProjectCell *cell = [tableView dequeueReusableCellWithIdentifier:newsProjectCellIdentifier];
    if(cell==nil)
    {
        NSArray *nib = [[NSBundle mainBundle]loadNibNamed:@"ProjectCell" owner:self options:nil];
        cell = [nib objectAtIndex:0];
    }
    [cell setData:self];
    return cell;
}
#pragma mark - BaseServiceDelegate
-(void)serviceResult:(ResponseVO *)result
{
    if(result.tag==CINT_TAG_GETCOMPANYDETAIL&&result.tag2==self.Companyid)
    {
        if ([result isSucess]) {
            ResponseVO *rvo = [[ResponseVO alloc] init];
            CompanyDetailVO *vo = [JsonParser parseJsonToEntity:result.msg respVO:&rvo ref:nil];
            if([rvo isSucess])
            {
                if(_comblock) _comblock(vo);
            }
            else
            {
                if(_errorblock) _errorblock(rvo.msg);
            }
        }
        else
        {
            if(_errorblock)
                _errorblock(result.msg);
        }
    }
}
@end
