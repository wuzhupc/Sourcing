//
//  TrainVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "TrainVO.h"
#import "TrainCell.h"
#import "StringUtil.h"
#import "HtmlUtil.h"
#import "MobileInfoService.h"
#import "ResponseVO.h"
#import "JsonParser.h"
#import "TrainDetailVO.h"

@interface TrainVO ()
{
@private
    NSInteger trainid;
}
@end

@implementation TrainVO

@synthesize trainname = trainname_;
-(void)setTrainid:(NSInteger )kid
{
    self.Id = kid;
    trainid = kid;
}
-(NSInteger )Trainid
{
    return trainid;
}

#pragma mark - Superclass Overrides
-(NSString *)getHtmlTitle
{
    return [NSString stringWithFormat:@"<br/><div align=\"center\"><font color=\"#111111\" size=\"4pt\"><strong>%@</strong></font></div><br/>",self.trainname];
}
-(NSString *)getHtmlSubTitle
{
    return [NSString stringWithFormat:@"<div style=\"height:0;border-bottom:1px solid #f00\"></div>"];
}

-(void)setHtmlToShow:(DetailInfoCompleteBlock)kcomblock failure:(DetailInfoErrorBlock)kerrorblock
{
    [super setHtmlToShow:kcomblock failure:kerrorblock];
    MobileInfoService *service = [[MobileInfoService alloc] initWithDelegate:self tag:CINT_TAG_GETTRAINDETAIL];
    service.tag2 = self.Trainid;
    [service getTrainDetail:[NSString stringWithFormat:@"%d",self.Trainid]];
}

-(NSString *)generateShareText
{
    return self.trainname;
}

-(CGFloat)heightForCell:(NSInteger)kindex
{
    return 44.0f;
}

-(UITableViewCell *)tableView:(UITableView *)tableView index:(NSInteger)kindex
{
    static NSString *newsTrainCellIdentifier = @"TrainCell";
    TrainCell *cell = [tableView dequeueReusableCellWithIdentifier:newsTrainCellIdentifier];
    if(cell==nil)
    {
        NSArray *nib = [[NSBundle mainBundle]loadNibNamed:@"TrainCell" owner:self options:nil];
        cell = [nib objectAtIndex:0];
    }
    [cell setData:self];
    return cell;
}

#pragma mark - BaseServiceDelegate
-(void)serviceResult:(ResponseVO *)result
{
    if(result.tag==CINT_TAG_GETTRAINDETAIL&&result.tag2==self.Trainid)
    {
        if ([result isSucess]) {
            ResponseVO *rvo = [[ResponseVO alloc] init];
            TrainDetailVO *vo = [JsonParser parseJsonToEntity:result.msg respVO:&rvo ref:nil];
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
