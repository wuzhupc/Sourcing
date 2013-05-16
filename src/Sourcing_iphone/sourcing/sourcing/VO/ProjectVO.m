//
//  ProjectVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "ProjectVO.h"
#import "ProjectCell.h"
#import "StringUtil.h"
#import "HtmlUtil.h"
#import "MobileInfoService.h"
#import "ResponseVO.h"
#import "JsonParser.h"
#import "ProjectDetailVO.h"

@interface ProjectVO ()
{
@private
    NSInteger projectid;
}
@end

@implementation ProjectVO

@synthesize projectname = projectname;
@synthesize publishtime = publishtime;
@synthesize projectstatus = projectstatus;

-(void)setProjectid:(NSInteger )kid
{
    self.Id = kid;
    projectid = kid;
}
-(NSInteger )Projectid
{
    return projectid;
}

#pragma mark - Superclass Overrides

-(NSString *)getHtmlTitle
{
    return [NSString stringWithFormat:@"<br/><div align=\"center\"><font color=\"#111111\" size=\"4pt\"><strong>%@</strong></font></div><br/>",self.projectname];
}
-(NSString *)getHtmlSubTitle
{
    return [NSString stringWithFormat:@"<div align=\"center\"><font color=\"#666666\" size=\"2pt\">%@&nbsp;&nbsp;%@</font></div><div style=\"height:0;border-bottom:1px solid #f00\"></div>",self.publishtime,self.projectstatus];
}

-(void)setHtmlToShow:(DetailInfoCompleteBlock)kcomblock failure:(DetailInfoErrorBlock)kerrorblock
{
    [super setHtmlToShow:kcomblock failure:kerrorblock];
    MobileInfoService *service = [[MobileInfoService alloc] initWithDelegate:self tag:CINT_TAG_GETPROJECTDETAIL];
    service.tag2 = self.Projectid;
    [service getProjectDetail:[NSString stringWithFormat:@"%d",self.Projectid]];
}

-(NSString *)generateShareText
{
    return self.projectname;
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
    if(result.tag==CINT_TAG_GETPROJECTDETAIL&&result.tag2==self.Projectid)
    {
        if ([result isSucess]) {
            ResponseVO *rvo = [[ResponseVO alloc] init];
            ProjectDetailVO *vo = [JsonParser parseJsonToEntity:result.msg respVO:&rvo ref:nil];
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
