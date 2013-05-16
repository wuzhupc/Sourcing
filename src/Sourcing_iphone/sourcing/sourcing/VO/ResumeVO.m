//
//  ResumeVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "ResumeVO.h"
#import "ProjectCell.h"
#import "StringUtil.h"
#import "HtmlUtil.h"
#import "MobileInfoService.h"
#import "ResponseVO.h"
#import "JsonParser.h"
#import "ResumeDetailVO.h"

@interface ResumeVO ()
{
@private
    NSInteger resumeid;
}

@end

@implementation ResumeVO

@synthesize  expectjob = expectjob_;
@synthesize name = name_;
@synthesize  publishtime = publishtime_;
@synthesize  resumetitle = resumetitle_;

-(void)setResumeid:(NSInteger )kid
{
    self.Id = kid;
    resumeid = kid;
}
-(NSInteger )Resumeid
{
    return resumeid;
}

#pragma mark - Superclass Overrides
-(NSString *)getHtmlTitle
{
    return [NSString stringWithFormat:@"<br/><div align=\"center\"><font color=\"#111111\" size=\"4pt\"><strong>%@</strong></font></div><br/>",self.resumetitle];
}
-(NSString *)getHtmlSubTitle
{
    return [NSString stringWithFormat:@"<div align=\"center\"><font color=\"#666666\" size=\"2pt\">%@&nbsp;&nbsp;%@</font></div><div style=\"height:0;border-bottom:1px solid #f00\"></div>",self.publishtime,self.name];
}

-(void)setHtmlToShow:(DetailInfoCompleteBlock)kcomblock failure:(DetailInfoErrorBlock)kerrorblock
{
    [super setHtmlToShow:kcomblock failure:kerrorblock];
    MobileInfoService *service = [[MobileInfoService alloc] initWithDelegate:self tag:CINT_TAG_GETRESUMEDETAIL];
    service.tag2 = self.Resumeid;
    [service getResumeDetail:[NSString stringWithFormat:@"%d",self.Resumeid]];
}

-(NSString *)generateShareText
{
    return self.resumetitle;
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
    if(result.tag==CINT_TAG_GETRESUMEDETAIL&&result.tag2==self.Resumeid)
    {
        if ([result isSucess]) {
            ResponseVO *rvo = [[ResponseVO alloc] init];
            ResumeDetailVO *vo = [JsonParser parseJsonToEntity:result.msg respVO:&rvo ref:nil];
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
